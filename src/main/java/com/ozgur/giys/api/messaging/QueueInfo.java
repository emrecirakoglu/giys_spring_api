package com.ozgur.giys.api.messaging;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueInfo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String vhost;
    private String routing_key;

}
