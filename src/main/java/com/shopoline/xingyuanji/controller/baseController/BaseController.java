package com.shopoline.xingyuanji.controller.baseController;


import com.shopoline.xingyuanji.config.Cors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class BaseController extends Cors implements Serializable {

    private static final long serialVersionUID = 7713281674907091541L;

    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

}
