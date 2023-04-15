package com.example.account;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("account")
public class ProfileConfiguration {
        private String title = "Account Default Service";
        private String descriptiveText = "default";


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescriptiveText() {
            return descriptiveText;
        }

        public void setDescriptiveText(String descriptiveText) {
            this.descriptiveText = descriptiveText;
        }
}
