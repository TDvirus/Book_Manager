package org.wayne.bookmanagermaster.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Book implements Serializable {
    private int bookId;
    private String bookAuthor;
    private String bookName;
    private String bookPrice;
    /**
     * {@link org.wayne.bookmanagermaster.model.enums.BookStatusEnum}
     */
    private int bookStatus;
}
