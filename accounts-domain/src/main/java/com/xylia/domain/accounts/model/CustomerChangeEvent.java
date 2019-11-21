package com.xylia.domain.accounts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerChangeEvent {

    @Id
    @JsonProperty
    private String id;

    @NotNull
    @JsonProperty
    private Name name;

    @NotNull
    @JsonProperty
    private Contact contact;

    @NotNull
    @JsonIgnore
    private List<Address> addressList;
}
