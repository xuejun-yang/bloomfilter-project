namespace java com.smarts.bloomfilter.common.thrift

struct FilterResult {
1: string key,
2: bool result
}

service Filter{

 bool  filterSingle(1:string key),
 
 list<FilterResult> filterList(1: list<FilterResult> keys)
}