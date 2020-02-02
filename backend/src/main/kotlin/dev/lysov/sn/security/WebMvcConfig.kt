package dev.lysov.sn.security

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    companion object {
        private const val MAX_AGE_SECS = 3600L
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .maxAge(MAX_AGE_SECS)
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/home/**").setViewName("forward:/")
        registry.addViewController("/account").setViewName("forward:/")
        registry.addViewController("/view-account/**").setViewName("forward:/")
        registry.addViewController("/signup").setViewName("forward:/")
    }
}