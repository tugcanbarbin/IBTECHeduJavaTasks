package net.javaguides.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "command")
public class Command implements IModel {
	@Id
	@Column(name = "name")
	private String alias;
	@Column(name = "packagename")
	private String packageName;
	@Column(name = "methodname")
	private String methodName;
	@Column(name = "parametertype")
	private String parameterType;
	@Column(name = "cust_id")
	private int custId;

	public Command() {}
	
	public Command(String alias, String packageName, String methodName, String parameterType, int custId) {
		super();
		this.alias = alias;
		this.packageName = packageName;
		this.methodName = methodName;
		this.parameterType = parameterType;
		this.custId = custId;
	}
	
	public String getAlias() {
		return alias;
	}

	public int getId() {
		return custId;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getMethodName() {
		return methodName;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setId(int id) {
		this.custId = id;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	@Override
	public String toString() {
		return "Command [alias=" + alias + ", packageName=" + packageName + ", methodName=" + methodName
				+ ", parameterType=" + parameterType + "]";
	}
	

}
