package com.car_workshop.Car.dto.v1.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private Boolean authenticated;
	private Date date;
	private Date expiration;
	private String accessToken;
	private String refreshToken;
	
	public TokenDto() {
	}

	public TokenDto(String userName, Boolean authenticated, Date date, Date expiration, String accessToken,
			String refreshToken) {
		this.userName = userName;
		this.authenticated = authenticated;
		this.date = date;
		this.expiration = expiration;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessToken, authenticated, date, expiration, refreshToken, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenDto other = (TokenDto) obj;
		return Objects.equals(accessToken, other.accessToken) && Objects.equals(authenticated, other.authenticated)
				&& Objects.equals(date, other.date) && Objects.equals(expiration, other.expiration)
				&& Objects.equals(refreshToken, other.refreshToken) && Objects.equals(userName, other.userName);
	}
}