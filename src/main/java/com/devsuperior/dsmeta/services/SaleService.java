package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<SaleReportDTO> findReport(String minDate, String maxDate, String name, Pageable pageable) {

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		// ternário
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate min = minDate.equals("") ? today.minusYears(1L) : LocalDate.parse(minDate);

		// 1º retorno entity depois transfiro to list DTO
		Page<Sale> result = repository.searchReport(min, max, name, pageable);
		Page<SaleReportDTO> dtos = result.map(x -> new SaleReportDTO(x));

		return dtos;
	}

	@Transactional(readOnly = true)
	public List<SaleSummaryDTO> findSummary(String minDate, String maxDate){
		
		LocalDate max = validMaxDate(maxDate);
		LocalDate min = validMinDate(minDate);

		List<SaleSummaryProjection> result = repository.searchSummary(min, max);
		List<SaleSummaryDTO> dtos = result.stream().map(x -> new SaleSummaryDTO(x)).collect(Collectors.toList());
		
		return dtos;
	}

	// VALIDATOR
	private LocalDate validMaxDate(String date){

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate valiDate = date.equals("") ? today : LocalDate.parse(date);
		
		return valiDate;
	}

	private LocalDate validMinDate(String date){
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate valiDate = date.equals("") ? today.minusYears(1L) : LocalDate.parse(date);

		return valiDate;
	}
}
