package com.vector.app.service;

import java.util.Map;

import org.springframework.lang.Nullable;
import org.springframework.ui.Model;

public interface UtilityService {
    String loadToPath(Model model,Map<String, ?> attributes,String path);
    String loadToPath(Model model,String attributeName,@Nullable Object attribute,String path);
    boolean authenticated();
}
