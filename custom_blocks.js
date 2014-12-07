Blockly.Blocks['rotate'] = {
    init: function() {
        this.setHelpUrl('https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#vsf9ko');
        this.setColour(270);
        this.appendValueInput("ANGLE")
            .setCheck("Number")
            .appendField("rotate");
        this.setPreviousStatement(true);
        this.setNextStatement(true);
        this.setTooltip('Set rocket rotation in % (-100%...100%)');
    }
};

Blockly.JavaScript['rotate'] = function(block) {
    var value_rotation = Blockly.JavaScript.valueToCode(block, 'ANGLE', Blockly.JavaScript.ORDER_ATOMIC);
    var code = 'this.setRotation(' + value_rotation + ');\n';
    return code;
};

Blockly.Blocks['speed'] = {
    init: function() {
        this.setHelpUrl('http://www.example.com/');
        this.setColour(270);
        this.appendValueInput("SPEED")
            .setCheck("Number")
            .appendField("speed");
        this.setPreviousStatement(true);
        this.setNextStatement(true);
        this.setTooltip('Set rocket rotation in % (0%...100%)');
    }
};

Blockly.JavaScript['speed'] = function(block) {
    var value_speed = Blockly.JavaScript.valueToCode(block, 'SPEED', Blockly.JavaScript.ORDER_ATOMIC);
    var code = 'this.setSpeed(' + value_speed + ');\n';
    return code;
};

Blockly.Blocks['radar_distance'] = {
    init: function() {
        this.setHelpUrl('https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#34jrbk');
        this.setColour(225);
        this.appendDummyInput()
            .appendField("radarDistance");
        this.setOutput(true, "Number");
        this.setTooltip('');
    }
};

Blockly.JavaScript['radar_distance'] = function(block) {
    var code = 'this.radar() ? this.radar().distance : 0';
    return [code, Blockly.JavaScript.ORDER_NONE];
};


Blockly.Blocks['radar_angle'] = {
    init: function() {
        this.setHelpUrl('https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#zzr2i2');
        this.setColour(225);
        this.appendDummyInput()
            .appendField("radarAngle");
        this.setOutput(true, "Number");
        this.setTooltip('');
    }
};

Blockly.JavaScript['radar_angle'] = function(block) {
    var code = 'this.radar() ? this.radar().angle : 0';
    return [code, Blockly.JavaScript.ORDER_NONE];
};


/*

Blockly.Blocks['distance'] = {
    init: function() {
        this.setHelpUrl('http://www.example.com/');
        this.setColour(20);
        this.appendDummyInput()
            .appendField("distance");
        this.setOutput(true, "Number");
        this.setTooltip('');
    }
};

Blockly.JavaScript['distance'] = function(block) {
    var code = 'this.getDistance()';
    return [code, Blockly.JavaScript.ORDER_NONE];
};


Blockly.Blocks['log'] = {
    init: function() {
        this.setHelpUrl('http://www.example.com/');
        this.setColour(290);
        this.appendValueInput("TEXT")
            .appendField("log");
        this.setPreviousStatement(true);
        this.setNextStatement(true);
        this.setTooltip('');
    }
};

Blockly.JavaScript['log'] = function(block) {
    var value_text = Blockly.JavaScript.valueToCode(block, 'TEXT', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = 'this.log(' + value_text + ');';
    return code;
};

    */