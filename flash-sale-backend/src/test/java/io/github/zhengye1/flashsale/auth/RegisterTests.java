package io.github.zhengye1.flashsale.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.github.zhengye1.flashsale.TestcontainersConfiguration;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class RegisterTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void cleanUp() {
        userRepository.deleteAll();
    }

    static Stream<String> validUsernames() {
        return Stream.of("abc", "a".repeat(50));
    }

    static Stream<String> invalidUsernames() {
        return Stream.of("ab", "a".repeat(51), "bob!", "");
    }

    static Stream<String> validPasswords() {
        return Stream.of("Passw0rd", "a".repeat(64));
    }

    static Stream<String> invalidPasswords() {
        return Stream.of("Passw0r", "a".repeat(65), "");
    }

    @Test
    void registerReturns201WithIdAndUsername() throws Exception {
        // Arrange
        String body = """
            {"username": "testUser", "password": "Passw0rd"}
            """;

        // Act + Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.passwordHash").doesNotExist());
    }

    @Test
    void registerReturns409WhenUsernameTaken() throws Exception {
        String body = """
            {"username": "alice", "password": "Passw0rd"}
            """;
        User existing = new User("alice", "hashPassword", Role.USER);
        userRepository.save(existing);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isConflict());
    }

    @Test
    void registerReturns409WhenUsernameDiffersOnlyInCase() throws Exception {
        String body = """
            {"username": "Alice", "password": "Passw0rd"}
            """;
        User existing = new User("alice", "hashPassword", Role.USER);
        userRepository.save(existing);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isConflict());
    }

    @Test
    void registerStoresPasswordAsBcryptHash() throws Exception {
        // Arrange
        String body = """
            {"username": "bob", "password": "Passw0rd"}
            """;

        // Act
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());

        // Assert
        User saved = userRepository.findByUsername("bob").orElseThrow();
        assertThat(saved.getPasswordHash()).isNotEqualTo("Passw0rd");
        assertThat(new BCryptPasswordEncoder().matches("Passw0rd", saved.getPasswordHash()))
                .isTrue();
    }

    @ParameterizedTest
    @MethodSource("validUsernames")
    void registerReturns201WhenUsernameAtBoundary(String username) throws Exception {
        String body = """
            {"username": "%s", "password": "Passw0rd"}
            """.formatted(username);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }

    @ParameterizedTest
    @MethodSource("invalidUsernames")
    void registerReturns400WhenUsernameInvalid(String username) throws Exception {
        String body = """
            {"username": "%s", "password": "Passw0rd"}
            """.formatted(username);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerReturns400WhenUsernameMissing() throws Exception {
        String body = """
            {"password": "Passw0rd"}
            """;
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource("validPasswords")
    void registerReturns201WhenPasswordValid(String password) throws Exception {
        String body = """
                {"username":"testUser", "password": "%s"}
            """.formatted(password);
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }

    @ParameterizedTest
    @MethodSource("invalidPasswords")
    void registerReturns400WhenPasswordInvalid(String password) throws Exception {
        String body = """
                {"username":"testUser", "password": "%s"}
            """.formatted(password);
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerReturns400WhenPasswordMissing() throws Exception {
        String body = """
            {"username":"testUser"}
            """;
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }
}
