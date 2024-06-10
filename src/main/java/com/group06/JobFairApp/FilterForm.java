package com.group06.JobFairApp;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterForm {
    private List<String> selectedFilters;
    private String selectedName;
}
