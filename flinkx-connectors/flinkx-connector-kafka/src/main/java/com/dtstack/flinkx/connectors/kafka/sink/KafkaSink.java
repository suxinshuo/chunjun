/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dtstack.flinkx.connectors.kafka.sink;

import com.dtstack.flinkx.conf.FlinkxConf;
import com.dtstack.flinkx.connectors.kafka.adapter.StartupModeAdapter;
import com.dtstack.flinkx.connectors.kafka.conf.KafkaConf;
import com.dtstack.flinkx.connectors.kafka.enums.StartupMode;
import com.dtstack.flinkx.sink.BaseDataSink;
import com.dtstack.flinkx.util.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.types.Row;

import java.util.Properties;

/**
 * Date: 2021/04/07
 * Company: www.dtstack.com
 *
 * @author tudou
 */
public class KafkaSink extends BaseDataSink {
    protected KafkaConf kafkaConf;

    public KafkaSink(FlinkxConf config) {
        super(config);
        Gson gson = new GsonBuilder().registerTypeAdapter(StartupMode.class, new StartupModeAdapter()).create();
        GsonUtil.setTypeAdapter(gson);
        kafkaConf = gson.fromJson(gson.toJson(config.getWriter().getParameter()), KafkaConf.class);
    }

    @Override
    public DataStreamSink<Tuple2<Boolean, Row>> writeData(DataStream<Tuple2<Boolean, Row>> dataSet) {
        Properties props = new Properties();
        props.putAll(kafkaConf.getProducerSettings());
//        FlinkKafkaProducer kafkaProducer = new FlinkKafkaProducer(kafkaConf.getTopic(), serializationMetricWrapper, props, Optional.of(new CustomerFlinkPartition<>()), KafkaUtil.getPartitionKeys(kafkaConf.getPartitionKeys()));

        return null;
    }
}