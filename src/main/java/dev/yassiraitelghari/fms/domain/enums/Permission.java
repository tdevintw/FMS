package dev.yassiraitelghari.fms.domain.enums;

public enum Permission {
    // Portfolio Owner Permissions

    PORTFOLIO_OWNER_PREVIEW_PORTFOLIO("portfolio_owner:preview_portfolio"),
    PORTFOLIO_OWNER_MANAGE_PERSONAL_INFO("portfolio_owner:manage_personal_info"),
    PORTFOLIO_OWNER_MANAGE_PROJECTS("portfolio_owner:manage_projects");



    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
