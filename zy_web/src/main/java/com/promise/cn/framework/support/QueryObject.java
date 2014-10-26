package com.promise.cn.framework.support;

import java.util.Iterator;
import java.util.List;

/**
 * key 字段名称
 * value 字段值
 * queryType  0等值查询 1模糊查询  2大于查询 3小于查询  5是in查询  6是not in查询 
 * **/
@SuppressWarnings("all")
public class QueryObject {

    private String key;
    private String value;
    private String queryType;//0等值查询 1模糊查询  2大于查询 3小于查询  5
    private String andOr;
    private String className;

    public String getClassName(){
        return this.className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public String getQueryType(){
        return queryType;
    }
    public void setQueryType(String queryType){
        this.queryType = queryType;
    }
    public String getKey(){
        return key;
    }
    public void setKey(String key){
        this.key = key;
    }
    public String getValue(){
        return value;
    }
    public void setValue(String value){
        this.value = value;
    }        
    public String getAndOr(){
        return andOr;
    }
    public void setAndOr(String andOr){
        this.andOr = andOr;
    }

    public static String creatSql(List values){
        List<QueryObject> list = values;
        StringBuffer sql=new StringBuffer();
        if (list != null && !list.isEmpty()) {
            Iterator<QueryObject> iterater = list.iterator();
            while (iterater.hasNext()) {
                QueryObject vo=iterater.next();
                String key = vo.getKey();
                String value = vo.getValue();
                String queryType=vo.getQueryType();
                String andOr = vo.getAndOr();
                if(!value.trim().equals("")){
                    if("OR".equalsIgnoreCase(andOr)){
                        if("0".equals(queryType)){
                            sql.append(" or " + key + "='" + value + "'");
                        }else if("1".equals(queryType)){
                            sql.append(" or " + key + " like '%" + value + "%'");
                        }
                        else if("2".equals(queryType)){
                            sql.append(" or convert(char(10),"+key+",20)>='"+value+"'");
                        }
                        else if("3".equals(queryType)){
                            sql.append(" or convert(char(10),"+key+",20)<='"+value+"'");
                        }
                        else if("4".equals(queryType)){
                            sql.append(" or "+key+" like '"+value+"%'");
                        }else if("5".equals(queryType)){
                            sql.append(" or "+key+" in ("+value+")");
                        }else if("6".equals(queryType)){
                            sql.append(" or "+key+" not in ("+value+")");
                        }
                    }else{
                        if("0".equals(queryType)){
                            sql.append(" and " + key + "='" + value + "'");
                        }else if("1".equals(queryType)){
                            sql.append(" and " + key + " like '%" + value + "%'");
                        }
                        else if("2".equals(queryType)){
                            sql.append(" and "+key+">='"+value+"'");
                        }
                        else if("3".equals(queryType)){
                            sql.append(" and "+key+"<='"+value+"'");
                        }
                        else if("4".equals(queryType)){
                            sql.append(" and "+key+" like '"+value+"%'");
                        }else if("5".equals(queryType)){
                            sql.append(" and "+key+" in ("+value+")");
                        }else if("6".equals(queryType)){
                            sql.append(" and "+key+" not in ("+value+")");
                        }
                    }
                }
            }
        }
        return sql.toString();
    }
    
    public static String createHibernateSql(List<QueryObject> values){
        List<QueryObject> list = values;
        StringBuffer sql=new StringBuffer();
        if (list != null && !list.isEmpty()) {
            QueryObject qo = list.get(0);
            sql.append(" select t from ").append(qo.getClassName()).append(" t where 1=1 ");
            Iterator<QueryObject> iterater = list.iterator();
            while (iterater.hasNext()) {
                QueryObject vo=iterater.next();
                String key = vo.getKey();
                String value = vo.getValue();
                String queryType=vo.getQueryType();
                String andOr = vo.getAndOr();
                if(!value.trim().equals("")){
                    if("OR".equalsIgnoreCase(andOr)){
                        if("0".equals(queryType)){
                            sql.append(" or " + key + "='" + value + "'");
                        }else if("1".equals(queryType)){
                            sql.append(" or " + key + " like '%" + value + "%'");
                        }
                        else if("2".equals(queryType)){
                            sql.append(" or convert(char(10),"+key+",20)>='"+value+"'");
                        }
                        else if("3".equals(queryType)){
                            sql.append(" or convert(char(10),"+key+",20)<='"+value+"'");
                        }
                        else if("4".equals(queryType)){
                            sql.append(" or "+key+" like '"+value+"%'");
                        }else if("5".equals(queryType)){
                            sql.append(" or "+key+" in ("+value+")");
                        }else if("6".equals(queryType)){
                            sql.append(" or "+key+" not in ("+value+")");
                        }
                    }else{
                        if("0".equals(queryType)){
                            sql.append(" and " + key + "='" + value + "'");
                        }else if("1".equals(queryType)){
                            sql.append(" and " + key + " like '%" + value + "%'");
                        }
                        else if("2".equals(queryType)){
                            sql.append(" and convert(char(10),"+key+",20)>='"+value+"'");
                        }
                        else if("3".equals(queryType)){
                            sql.append(" and convert(char(10),"+key+",20)<='"+value+"'");
                        }
                        else if("4".equals(queryType)){
                            sql.append(" and "+key+" like '"+value+"%'");
                        }else if("5".equals(queryType)){
                            sql.append(" and "+key+" in ("+value+")");
                        }else if("6".equals(queryType)){
                            sql.append(" and "+key+" not in ("+value+")");
                        }
                    }
                }
            }
        }
        return sql.toString();
    }
}
