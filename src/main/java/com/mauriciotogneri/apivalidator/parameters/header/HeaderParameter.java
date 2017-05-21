package com.mauriciotogneri.apivalidator.parameters.header;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class HeaderParameter extends HashMap<String, String> implements Iterable<Entry<String, String>>
{
    public HeaderParameter(Object object)
    {
        fill(object);
    }

    private void fill(Object object)
    {
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields)
        {
            try
            {
                Object value = field.get(object);

                if (value != null)
                {
                    put(field.getName(), value.toString());
                }
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Iterator<Entry<String, String>> iterator()
    {
        return entrySet().iterator();
    }
}