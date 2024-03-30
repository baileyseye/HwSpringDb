package org.baileyseye.hwspringdb.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorDTOTest {

    @Test
    public void testGetAndSetName() {
        AuthorDTO authorDTO = new AuthorDTO();
        String expectedName = "John Doe";
        authorDTO.setName(expectedName);

        assertEquals(expectedName, authorDTO.getName());
    }

    @Test
    public void testGetAndSetCategoryId() {
        AuthorDTO authorDTO = new AuthorDTO();
        Integer expectedCategoryId = 1;
        authorDTO.setCategoryId(expectedCategoryId);

        assertEquals(expectedCategoryId, authorDTO.getCategoryId());
    }

    @Test
    public void testConstructorWithName() {
        String name = "Jane Doe";
        AuthorDTO authorDTO = new AuthorDTO(name);

        assertEquals(name, authorDTO.getName());
        assertEquals(2, authorDTO.getCategoryId());
    }
}