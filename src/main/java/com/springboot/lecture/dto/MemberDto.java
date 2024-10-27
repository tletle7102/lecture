package com.springboot.lecture.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="member dto관련 설명")
public class MemberDto {

    @Schema(description="이름")
    private String name;
    @Schema(
            description="이메일",
            maxLength = 50,
            example = "test@test.com")
    private String email;
    @Schema(
            description="조직",
            allowableValues={"100","200"})
    private String organization;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}

