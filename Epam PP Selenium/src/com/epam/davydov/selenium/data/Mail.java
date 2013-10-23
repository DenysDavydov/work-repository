package com.epam.davydov.selenium.data;

import com.epam.davydov.selenium.utils.Settings;

public class Mail {
	public String destEmail;
	public String subject;
	public String body;

	public Mail(String destEmail, String subject, String body) {
		this.destEmail = destEmail;
		this.subject = subject;
		this.body = body;
	}

	public Mail() {
		this.destEmail = Settings.getDestinationEmail();
		this.subject = Settings.getMailSubject();
		this.body = Settings.getMailBody();
	}

	@Override
	public String toString() {
		return "Mail [destEmail=" + destEmail + ", subject=" + subject + ", body=" + body + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((destEmail == null) ? 0 : destEmail.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mail other = (Mail) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (destEmail == null) {
			if (other.destEmail != null)
				return false;
		} else if (!destEmail.equals(other.destEmail))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
}