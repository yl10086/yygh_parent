import request from '@/utils/request'

/**
 * current,size,searchObj
 *    每页,几条,传的实体类
 */
export default {
  //列表查询
  getHospSetList(current,limit,searchObj){
    return request({
      url: `/admin/hosp/hospitalSet/selectPage/${current}/${limit}`,
      method: 'post',
      data: searchObj //使用json传递实体类
    })
  },
  //删除单个
  deleteHospSetId(id){
    return request({
      url: `/admin/hosp/hospitalSet/${id}`,
      method: 'delete'
    })
  },
  //批量删除
  batchRemoveHospSetId(idList){
    return request({
      url: `/admin/hosp/hospitalSet/batchDeleteHospitalSet`,
      method: 'delete',
      data: idList
    })
  },
  //锁定与解锁
  lockHospitalSet(id,status){
    return request({
      url: `/admin/hosp/hospitalSet/lockHospitalSet/${id}/${status}`,
      method: 'put'
    })
  },
  //添加医院信息
  addHospitalSet(hospitalSet){
    return request({
      url: `/admin/hosp/hospitalSet/addHospitalSet`,
      method: 'post',
      data: hospitalSet
    })
  },
  //根据医院id查询操作
  getHospitalSet(id){
    return request({
      url: `/admin/hosp/hospitalSet/getHospitalSet/${id}`,
      method: 'get'
    })
  },
  //修改医院信息
  updateHospitalSet(hospitalSet){
    return request({
      url: `/admin/hosp/hospitalSet/updateHospitalSet`,
      method: 'post',
      data: hospitalSet
    })
  }
}
