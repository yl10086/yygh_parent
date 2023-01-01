import request from '@/utils/request'

export default {
  //医院列表条件分页查询
  getHospList(page,limit,searchObj){
    return request({
      url: `/admin/hosp/hospital/list/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  },
  //根据dictCode查询所有子节点(所有省份)
  findByDictCode(dictCode) {
    return request({
      url: `/admin/cmn/dict/findByDictCode/${dictCode}`,
      method: 'get'
    })
  },
  //根据id查询下级数据字典
  findByChildId(id) {
    return request({
      url: `/admin/cmn/dict/findChildData/${id}`,
      method: 'get'
    })
  }, 
  //更新医院状态
  updateHospStatus(id,status) {
    return request({
      url: `/admin/hosp/hospital/updateHospStatus/${id}/${status}`,
      method: 'get'
    })
  },
  //查看医院详情
  getHospById(id){
    return request({
      url: `/admin/hosp/hospital/showHospDetail/${id}`,
      method: 'get'
    })
  },
  //根据医院编号，查询医院所有科室列表
  getDepartmentByHoscode(hoscode){
    return request({
      url: `/admin/hosp/department/getDepartmentList/${hoscode}`,
      method: 'get'
    })
  },
  //查询预约规则
  getScheduleRule(page, limit, hoscode, depcode) {
    return request({
     url: `/admin/hosp/schedule/getScheduleRule/${page}/${limit}/${hoscode}/${depcode}`,
     method: 'get'
    })
  },
  //查询排班详情
  getScheduleDetail(hoscode, depcode,workDate) {
    return request({
     url: `/admin/hosp/schedule/getScheduleDetail/${hoscode}/${depcode}/${workDate}`,
     method: 'get'
    })
  },
}
