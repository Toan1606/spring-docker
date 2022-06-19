package com.codedecode.demo.dto;

import java.util.List;

import com.codedecode.demo.entity.Posting;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class PostingHomePage {

	private List<Posting> attractiveJob;
	private  List<Posting> urgentRecruitment;
}
