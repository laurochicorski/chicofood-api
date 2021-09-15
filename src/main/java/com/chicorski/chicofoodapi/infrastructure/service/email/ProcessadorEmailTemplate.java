package com.chicorski.chicofoodapi.infrastructure.service.email;

import com.chicorski.chicofoodapi.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
public class ProcessadorEmailTemplate {

    @Autowired
    private Configuration freemakerConfig;

    protected String processarTemplate(EnvioEmailService.Mensagem mensagem) {
        try {
            Template template = freemakerConfig.getTemplate(mensagem.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template,
                    mensagem.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Não foi possível montar template do e-mail", e);
        }
    }
}
