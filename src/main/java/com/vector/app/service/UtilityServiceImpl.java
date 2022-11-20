package com.vector.app.service;

import java.util.Map;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UtilityServiceImpl implements UtilityService{

    @Override
    public String loadToPath(Model model, Map<String, ?> attributes, String path) {
        return null;
    }

    @Override
    public String loadToPath(Model model, String attributeName, @Nullable Object attribute, String path) {
        model.addAttribute(attributeName, attribute);
        return path;
    }

    @Override
    public boolean authenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth!=null;
    }
}
