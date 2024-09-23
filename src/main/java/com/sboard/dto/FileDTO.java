package com.sboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FileDTO {

    private int fno;
    private int ano;
    private String oName;
    private String sName;
    private int download;
    private String rdate;

}
