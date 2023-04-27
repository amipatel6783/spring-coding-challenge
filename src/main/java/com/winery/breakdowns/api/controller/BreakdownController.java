package com.winery.breakdowns.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winery.breakdowns.api.dto.BreakdownDto;
import com.winery.breakdowns.api.dto.BreakdownResponse;
import com.winery.breakdowns.api.dto.ComponentDto;
import com.winery.breakdowns.api.dto.WineDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/breakdown")
public class BreakdownController {

	
	// for perc-year based breakdown
	@GetMapping(value = "/year/{lotCode}")
	public ResponseEntity<BreakdownResponse> getBreakdownByYear(@PathVariable("lotCode") String lc) throws IOException {
		ClassLoader cl = getClass().getClassLoader();
		URL r1 = cl.getResource(lc + ".json");
		WineDto w = new ObjectMapper().readValue(r1, WineDto.class);

		if (w == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
		}

		if (!Arrays.asList("year", "variety", "region", "year-variety").contains("year")) {
			throw new IllegalArgumentException("Received wrong type: " + "year");
		}

		BreakdownResponse r = build("year",
				w.components.stream().sorted(Comparator.comparing((ComponentDto c) -> c.percentage).reversed()));
		
		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	// for perc-variety based breakdown
	@GetMapping(value = "/variety/{lotCode}")
	public ResponseEntity<BreakdownResponse> getBreakdownByVariety(@PathVariable("lotCode") String lc)
			throws IOException {
		ClassLoader cl = getClass().getClassLoader();
		URL r1 = cl.getResource(lc + ".json");
		WineDto w = new ObjectMapper().readValue(r1, WineDto.class);

		if (w == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
		}

		if (!Arrays.asList("year", "variety", "region", "year-variety").contains("variety")) {
			throw new IllegalArgumentException("Received wrong type: " + "variety");
		}

		BreakdownResponse r = build("variety",
				w.components.stream().sorted(Comparator.comparing((ComponentDto c) -> c.percentage).reversed()));

		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	// for perc-region based breakdown
	@GetMapping(value = "/region/{lotCode}")
	public ResponseEntity<BreakdownResponse> getBreakdownByRegion(@PathVariable("lotCode") String lc)
			throws IOException {
		ClassLoader cl = getClass().getClassLoader();
		URL r1 = cl.getResource(lc + ".json");
		WineDto w = new ObjectMapper().readValue(r1, WineDto.class);

		if (w == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
		}

		if (!Arrays.asList("year", "variety", "region", "year-variety").contains("region")) {
			throw new IllegalArgumentException("Received wrong type: " + "region");
		}

		BreakdownResponse r = build("region",
				w.components.stream().sorted(Comparator.comparing((ComponentDto c) -> c.percentage).reversed()));

		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	// for perc-year-variety based breakdown
	@GetMapping(value = "/year-variety/{lotCode}")
	public ResponseEntity<BreakdownResponse> getBreakdownByYearAndVariety(@PathVariable("lotCode") String lc)
			throws IOException {
		ClassLoader cl = getClass().getClassLoader();
		URL r1 = cl.getResource(lc + ".json");
		WineDto w = new ObjectMapper().readValue(r1, WineDto.class);

		if (w == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
		}

		if (!Arrays.asList("year", "variety", "region", "year-variety").contains("year-variety")) {
			throw new IllegalArgumentException("Received wrong type: " + "year-variety");
		}

		BreakdownResponse r = build("year-variety",
				w.components.stream().sorted(Comparator.comparing((ComponentDto c) -> c.percentage).reversed()));

		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	public BreakdownResponse buildYearBreakdown(Stream<ComponentDto> cds) {

		Map<String, Double> b = cds.map(o -> new BreakdownDto(String.valueOf(o.percentage), String.valueOf(o.year)))
				.collect(Collectors.toList()).stream().collect(Collectors.groupingBy(BreakdownDto::getKey,
						Collectors.summingDouble(o -> Double.parseDouble(o.getPercentage()))));

		List<BreakdownDto> list = new ArrayList<BreakdownDto>();
		b.entrySet().forEach(el -> {
			BreakdownDto bd = new BreakdownDto(el.getValue().toString(), el.getKey().toString());
			list.add(bd);
		});
		return new BreakdownResponse("year", list);
	}

	public BreakdownResponse buildVarietyBreakdown(Stream<ComponentDto> cds) {

		Map<String, Double> b = cds.map(o -> new BreakdownDto(String.valueOf(o.percentage), o.variety))
				.collect(Collectors.toList()).stream().collect(Collectors.groupingBy(BreakdownDto::getKey,
						Collectors.summingDouble(o -> Double.parseDouble(o.getPercentage()))));

		List<BreakdownDto> list = new ArrayList<BreakdownDto>();
		b.entrySet().forEach(el -> {
			BreakdownDto bd = new BreakdownDto(el.getValue().toString(), el.getKey().toString());
			list.add(bd);
		});
		return new BreakdownResponse("variety", list);
	}

	public BreakdownResponse buildRegionBreakdown(Stream<ComponentDto> cds) {
		Map<String, Double> b = cds.map(o -> new BreakdownDto(String.valueOf(o.percentage), o.region))
				.collect(Collectors.toList()).stream().collect(Collectors.groupingBy(BreakdownDto::getKey,
						Collectors.summingDouble(o -> Double.parseDouble(o.getPercentage()))));

		List<BreakdownDto> list = new ArrayList<BreakdownDto>();
		b.entrySet().forEach(el -> {
			BreakdownDto bd = new BreakdownDto(el.getValue().toString(), el.getKey().toString());
			list.add(bd);
		});
		return new BreakdownResponse("region", list);
	}

	public BreakdownResponse buildYearVarietyBreakdown(Stream<ComponentDto> cds) {

		Map<String, Double> b = cds
				.map(o -> new BreakdownDto(String.valueOf(o.percentage), String.format("%d-%s", o.year, o.variety)))
				.collect(Collectors.toList()).stream().collect(Collectors.groupingBy(BreakdownDto::getKey,
						Collectors.summingDouble(o -> Double.parseDouble(o.getPercentage()))));

		List<BreakdownDto> list = new ArrayList<BreakdownDto>();
		b.entrySet().forEach(el -> {
			BreakdownDto bd = new BreakdownDto(el.getValue().toString(), el.getKey().toString());
			list.add(bd);
		});

		return new BreakdownResponse("year-variety", list);
	}

	public BreakdownResponse build(String t, Stream<ComponentDto> cds) {
		if (t.equals("year")) {
			return buildYearBreakdown(cds);
		} else if (t.equals("region")) {
			return buildRegionBreakdown(cds);
		} else if (t.equals("variety")) {
			return buildVarietyBreakdown(cds);
		} else if (t.equals("year-variety")) {
			return buildYearVarietyBreakdown(cds);
		}

		throw new IllegalArgumentException("Received invalid type: " + t);
	}

}
