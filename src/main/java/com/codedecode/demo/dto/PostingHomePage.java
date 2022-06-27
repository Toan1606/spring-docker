package com.codedecode.demo.dto;

import java.util.List;

import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.Posting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class PostingHomePage {
	private List<Posting> attractiveJob;
	private List<Posting> urgentRecruitment;
	private List<Address> jobByProvince; 
}
