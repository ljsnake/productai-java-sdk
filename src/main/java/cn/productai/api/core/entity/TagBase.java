package cn.productai.api.core.entity;

import cn.productai.api.core.ISearchTag;
import cn.productai.api.core.enums.TagOperator;
import cn.productai.api.core.helper.EnumHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Thinkpad on 2017/7/3.
 */
public abstract class TagBase implements ISearchTag {
    private static HashMap<Integer, String> _tagOperatorDic = EnumHelper.toHashMap(TagOperator.class);

    private String _operator = "and";
    private ArrayList<Object> tags = new ArrayList<>();

    public TagBase(TagOperator operator) {
        this._operator = _tagOperatorDic.get(operator.ordinal());
    }

    @Override
    public void Add(ISearchTag tag) {
        tags.add(tag);
    }

    @Override
    public void Add(String tagString) {
        if (tagString.isEmpty())
            throw new IllegalArgumentException("tag can not be null or empty");

        tags.add(tagString);
    }

    @Override
    public void Add(ArrayList<String> tags) {
        if (tags != null && tags.size() > 0) {
            for (String s : tags) {
                this.Add(s);
            }
        }
    }

    @Override
    public String toString() {
        String s = String.format("\"%s\":[", this._operator);
        for (Object tag : tags) {
            String typeName = tag.getClass().getTypeName();
            if (typeName == String.class.getTypeName()) {
                s += String.format("\"%s\",", tag);
            } else if (ISearchTag.class.isAssignableFrom(tag.getClass())) {
                s += String.format("{%s}", tag.toString());
            }
        }
        s = s.substring(0, s.length() - 1);
        return s + "]";
    }
}
