package io.github.devclubspb.parking.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyBranchResponse {

    private Long id;
    private String name;
    private String address;

}
