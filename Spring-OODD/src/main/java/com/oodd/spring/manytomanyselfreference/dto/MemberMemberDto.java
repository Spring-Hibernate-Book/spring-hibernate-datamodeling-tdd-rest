package com.oodd.spring.manytomanyselfreference.dto;


public class MemberMemberDto {
	private MemberDto memberId1;
	private MemberDto memberId2;
	
	public MemberDto getMemberId1() {
		return memberId1;
	}
	public void setMemberId1(MemberDto memberId1) {
		this.memberId1 = memberId1;
	}
	public MemberDto getMemberId2() {
		return memberId2;
	}
	public void setMemberId2(MemberDto memberId2) {
		this.memberId2 = memberId2;
	}
}