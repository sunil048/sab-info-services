package com.sabtok.report;

import java.io.Serializable;

	public class ReportData implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String reportId;
		String reportname;
		String createdDate;
		String createdBy;
		String modifiedDate;
		String modifiedBy;
		String component;
		String sub_component;
		String status;
		String inputData;
		String taskjobId;
		public String exceptionjobId;
		@Override
		public String toString() {
			return "ReportData [reportId=" + reportId + ", reportname=" + reportname + ", createdDate=" + createdDate
					+ ", component=" + component + ", sub_component=" + sub_component + ", status=" + status + "]";
		}
		
	}
