package com.neosoft.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "userdetail")
@Data
public class UserDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;
	@Column(name="username",length = 10, nullable = false)
	private String username;
	@Column(name="emailid",length = 20, nullable = false)
	private String emailid;
	@Column(name="password",length = 20, nullable = false)
	private String password;
	@Column(name="surname",length = 10, nullable = false)
	private String surname;
	@Column(name="pincode",length = 30, nullable = false)
	private Long pincode;
	@Column(name="dateofbirth",length = 10, nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date dateofbirth;
	@Column(name="Joiningdate",length = 20, nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date Joiningdate;
	@Column(name="active",length = 2, nullable = false)
	private char active;
	
	
	@Override
	public String toString() {
		return "UserDetail [userid=" + userid + ", username=" + username + ", emailid=" + emailid + ", password="
				+ password + ", surname=" + surname + ", pincode=" + pincode + ", dateofbirth=" + dateofbirth
				+ ", Joiningdate=" + Joiningdate + ", active=" + active + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetail other = (UserDetail) obj;
		if (Joiningdate == null) {
			if (other.Joiningdate != null)
				return false;
		} else if (!Joiningdate.equals(other.Joiningdate))
			return false;
		if (active != other.active)
			return false;
		if (dateofbirth == null) {
			if (other.dateofbirth != null)
				return false;
		} else if (!dateofbirth.equals(other.dateofbirth))
			return false;
		if (emailid == null) {
			if (other.emailid != null)
				return false;
		} else if (!emailid.equals(other.emailid))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Joiningdate == null) ? 0 : Joiningdate.hashCode());
		result = prime * result + active;
		result = prime * result + ((dateofbirth == null) ? 0 : dateofbirth.hashCode());
		result = prime * result + ((emailid == null) ? 0 : emailid.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	

}
