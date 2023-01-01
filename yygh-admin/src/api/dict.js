import request from '@/utils/request'

export default {
  //列表查询
  dictList(id){
    return request({
      url: `/admin/cmn/dict/findChildData/${id}`,
      method: 'get'
    })
  }
}
