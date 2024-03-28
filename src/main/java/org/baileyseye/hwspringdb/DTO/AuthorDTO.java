package org.baileyseye.hwspringdb.DTO;

public class AuthorDTO {
    private String name;
    private Integer categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public AuthorDTO(String name) {
        this.name = name;
        this.categoryId = 2;
    }

    public AuthorDTO(){}
}
