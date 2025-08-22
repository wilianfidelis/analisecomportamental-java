package com.tecnodata.loja.service;

import com.tecnodata.loja.entities.EmailEntity;
import com.tecnodata.loja.enums.StatusEmail;
import com.tecnodata.loja.repository.EmailRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public EmailEntity sendEmail(EmailEntity emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getRemetente());
            message.setTo(emailModel.getDestinatario());
            message.setSubject(emailModel.getAssunto());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.ENVIADO);
        } catch (MailException e) {
            log.error(e.getMessage());
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

    public EmailEntity sendEmailTemplate(EmailEntity emailModel, @NotNull Context context, String template) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        try {
            emailModel.setSendDateEmail(LocalDateTime.now());

            String html = templateEngine.process(template, context);
            helper.setTo(emailModel.getDestinatario());
            helper.setText(html, true);
            helper.setSubject(emailModel.getAssunto());
            helper.setFrom(emailModel.getRemetente());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.ENVIADO);
        } catch (MailException e) {
            log.error(e.getMessage());
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

    public Page<EmailEntity> findAll(Pageable pageable) {
        return emailRepository.findAll(pageable);
    }

    public Optional<EmailEntity> findById(Long emailId) {
        return emailRepository.findById(emailId);
    }
}
