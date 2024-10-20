package com.sabtok.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "USER_STORIES")
public class UserStory implements Serializable {
        @Id
        private String storyNumber;
        private String discription;
        private String projectName;
        private String startDate;
        private String createDate;
        private String updateDate;
        private String closedDate;
        private int storyPoint;
        private String targetDate;
        private String developingSkill;
        private String assigned;
        private String gitBranch;
        private String gitUrl;
        private String status;
        private String epic;
        private String project;
        private String severity;
        private String expectedFixedVersion;
        private String actualFixedVersion;
        private String linkedStories;
        private Integer effort;
        private String lables;
}
