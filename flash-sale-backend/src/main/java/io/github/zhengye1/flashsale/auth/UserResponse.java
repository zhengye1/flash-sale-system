package io.github.zhengye1.flashsale.auth;

public record UserResponse(Long id, String username, Role role) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getRole());
    }
}
