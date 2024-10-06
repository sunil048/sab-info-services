package com.sabtok.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PAGE_LINKAGE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageLinkage extends Traceable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAGE_LINKAGE_SEQ")
    @SequenceGenerator(name = "PAGE_LINKAGE_SEQ", sequenceName = "PAGE_LINKAGE_SEQ", allocationSize = 1,initialValue = 1)
    private Long linkId;
    private String pageId;
    private String itemId;
    private LinkageType linkageType;
    private String Description;

}
