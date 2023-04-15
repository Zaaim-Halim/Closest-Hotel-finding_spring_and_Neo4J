package com.midvi.streetMapModels;

import java.io.Serializable;
import java.util.List;

public class relation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	List<member> member;
	private List<tag> tag;
	public relation() {
		super();
		
	}
	public relation(Long id, List<com.midvi.streetMapModels.member> member, List<com.midvi.streetMapModels.tag> tag) {
		super();
		this.id = id;
		this.member = member;
		this.tag = tag;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<member> getMember() {
		return member;
	}
	public void setMember(List<member> member) {
		this.member = member;
	}
	public List<tag> getTag() {
		return tag;
	}
	public void setTag(List<tag> tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "relation [id=" + id + ", member=" + member + ", tag=" + tag + "]";
	}
	
	
	
}
