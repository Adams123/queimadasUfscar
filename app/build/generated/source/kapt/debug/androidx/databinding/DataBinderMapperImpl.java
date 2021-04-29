package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.ufscar.queimadas_front.DataBinderMapperImpl());
  }
}
