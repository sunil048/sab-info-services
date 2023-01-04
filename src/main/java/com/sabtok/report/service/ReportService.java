package com.sabtok.report.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.sabtok.entity.Page;

@Service
public interface ReportService {

	public Page getPageDetails(String pageId);
}
