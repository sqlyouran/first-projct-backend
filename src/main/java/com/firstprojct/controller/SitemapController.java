package com.firstprojct.controller;

import com.firstprojct.model.Hospital;
import com.firstprojct.model.Specialty;
import com.firstprojct.repository.HospitalRepository;
import com.firstprojct.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SitemapController {

    @Value("${app.site-url:http://localhost:5173}")
    private String siteUrl;

    private final HospitalRepository hospitalRepository;
    private final SpecialtyRepository specialtyRepository;

    public SitemapController(HospitalRepository hospitalRepository, SpecialtyRepository specialtyRepository) {
        this.hospitalRepository = hospitalRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String generateSitemap() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        List<Specialty> specialties = specialtyRepository.findAll();

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");

        // Homepage
        sb.append("  <url><loc>").append(siteUrl).append("/</loc></url>\n");

        // Hospital pages
        for (Hospital h : hospitals) {
            sb.append("  <url><loc>").append(siteUrl).append("/hospitals/").append(h.getId()).append("</loc></url>\n");
        }

        // Specialty ranking pages
        for (Specialty s : specialties) {
            sb.append("  <url><loc>").append(siteUrl).append("/specialties/").append(s.getId()).append("</loc></url>\n");
        }

        // Community page
        sb.append("  <url><loc>").append(siteUrl).append("/community</loc></url>\n");

        sb.append("</urlset>");
        return sb.toString();
    }
}
