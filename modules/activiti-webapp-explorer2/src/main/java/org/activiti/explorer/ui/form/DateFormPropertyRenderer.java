/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.explorer.ui.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.impl.form.DateFormType;

import com.vaadin.ui.Component;
import com.vaadin.ui.PopupDateField;


/**
 * @author Frederik Heremans
 */
public class DateFormPropertyRenderer extends AbstractFormPropertyRenderer {

  public DateFormPropertyRenderer() {
    super(DateFormType.class);
  }

  @Override
  public Component getComponentProperty(FormProperty formProperty) {
    Component component = null;
    if(formProperty.isWritable()) {
      // Writable string
      PopupDateField dateField = new PopupDateField();
      String datePattern = (String) formProperty.getType().getInformation("datePattern");
      dateField.setDateFormat(datePattern);
      
      if(formProperty.getValue() != null) {
        // Try parsing the current value
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        
        try {
          Date date = dateFormat.parse(formProperty.getValue());
          dateField.setValue(date);
        } catch (ParseException e) {
          // TODO: what todo when current value is illegal date?
        }
      }
      component = dateField;
    } else {
      component = getDefaultReadonlyPropertyComponent(formProperty);
    }
    return component;
  }

}