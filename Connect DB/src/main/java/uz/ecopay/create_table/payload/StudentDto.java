package uz.ecopay.create_table.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private String firstname;
    private String lastname;
    private int age;
    private String phoneNumber;
}
