"use strict";

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
    return 'this.setRotation(' + value_rotation + ');\n';
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
    return 'this.setSpeed(' + value_speed + ');\n';
};

Blockly.Blocks['radar_distance'] = {
    init: function() {
        this.setHelpUrl('https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#34jrbk');
        this.setColour(225);
        this.appendDummyInput()
            .appendField("radarDistance");
        this.setOutput(true, "Number");
        this.setTooltip('Get distance to nearest space object');
    }
};

Blockly.JavaScript['radar_distance'] = function () {
    var functionName = Blockly.JavaScript.provideFunction_('radar_distance',
        [ 'function ' + Blockly.JavaScript.FUNCTION_NAME_PLACEHOLDER_ + '(ctrl) {',
            '  var radar = ctrl.radar();',
            '  return !!radar ? radar.distance : 0;',
            '}']);
    var code = functionName + '(this)';
    return [code, Blockly.JavaScript.ORDER_FUNCTION_CALL];
};


Blockly.Blocks['radar_angle'] = {
    init: function() {
        this.setHelpUrl('https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#zzr2i2');
        this.setColour(225);
        this.appendDummyInput()
            .appendField("radarAngle");
        this.setOutput(true, "Number");
        this.setTooltip('Get relative angle to nearest space object');
    }
};

Blockly.JavaScript['radar_angle'] = function() {
    var functionName = Blockly.JavaScript.provideFunction_('radar_angle',
        [ 'function ' + Blockly.JavaScript.FUNCTION_NAME_PLACEHOLDER_ + '(ctrl) {',
            '  var radar = ctrl.radar();',
            '  return !!radar ? radar.angle : 0;',
            '}']);
    var code = functionName + '(this)';
    return [code, Blockly.JavaScript.ORDER_FUNCTION_CALL];
};


Blockly.Blocks['log'] = {
    init: function() {
        this.setHelpUrl('http://www.example.com/');
        this.setColour(45);
        this.appendValueInput("TEXT")
            .appendField("log");
        this.setPreviousStatement(true);
        this.setNextStatement(true);
        this.setTooltip('Log value');
    }
};

Blockly.JavaScript['log'] = function(block) {
    var value_text = Blockly.JavaScript.valueToCode(block, 'TEXT', Blockly.JavaScript.ORDER_ATOMIC);
    return 'this.log(' + value_text + ');\n';
};


Blockly.Blocks['map'] = {
    init: function() {
        this.setHelpUrl('http://www.example.com/');
        this.setColour(330);
        this.appendValueInput("VALUE")
            .setCheck("Number")
            .appendField("map");
        this.appendDummyInput()
            .appendField("[")
            .appendField(new Blockly.FieldTextInput("-180"), "FROM_MIN");
        this.appendDummyInput()
            .appendField(":")
            .appendField(new Blockly.FieldTextInput("180"), "FROM_MAX");
        this.appendDummyInput()
            .appendField("] => [")
            .appendField(new Blockly.FieldTextInput("-50"), "TO_MIN");
        this.appendDummyInput()
            .appendField(":")
            .appendField(new Blockly.FieldTextInput("50"), "TO_MAX")
            .appendField("]");
        this.setInputsInline(true);
        this.setOutput(true, "Number");
        this.setTooltip('Convert value from one range to another');
    }
};


Blockly.JavaScript['map'] = function(block) {
    var value_value = Blockly.JavaScript.valueToCode(block, 'VALUE', Blockly.JavaScript.ORDER_ATOMIC);
    var text_from_max = block.getFieldValue('FROM_MAX');
    var text_to_max = block.getFieldValue('TO_MAX');
    var text_to_min = block.getFieldValue('TO_MIN');
    var text_from_min = block.getFieldValue('FROM_MIN');
    var functionName = Blockly.JavaScript.provideFunction_('map',
        [ 'function ' + Blockly.JavaScript.FUNCTION_NAME_PLACEHOLDER_ + '(value, min1, max1, min2, max2) {',
            '  if (value <= min1) return min2;',
            '  if (value >= max1) return max2;',
            '  return (value - min1) / (max1 - min1) * (max2 - min2) + min2;',
            '}']);
    var code = functionName + '(' + value_value + ', ' + text_from_min + ', '+ text_from_max + ',' + text_to_min + ', '+ text_to_max + ')';
    return [code, Blockly.JavaScript.ORDER_FUNCTION_CALL];
};


Blockly.Blocks['border_distance'] = {
    init: function() {
        this.setHelpUrl('http://www.example.com/');
        this.setColour(20);
        this.appendDummyInput()
            .appendField("borderDistance");
        this.setOutput(true, "Number");
        this.setTooltip('Distance to screen border');
    }
};

Blockly.JavaScript['border_distance'] = function() {
    var code = 'this.getDistance()';
    return [code, Blockly.JavaScript.ORDER_NONE];
};

