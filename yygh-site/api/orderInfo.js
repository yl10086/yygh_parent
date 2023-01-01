import request from '@/utils/request'

const api_name = '/api/order/orderInfo'

export default {
    //生成订单
    createOrders(scheduleId,patientId) {
        return request ({
            url: `${api_name}/auth/submitOrder/${scheduleId}/${patientId}`,
            method: 'post'
        })
    },
    //根据订单id查询订单详情
    getOrderInfo(orderId) {
        return request ({
            url: `${api_name}/auth/getOrderInfo/${orderId}`,
            method: 'get'
        })
    },
    //条件查询订单列表（分页）
    getPageList(page, limit, searchObj) {
        return request ({
            url: `${api_name}/auth/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },
    //查询订单状态
    getStatusList() {
        return request({
            url: `${api_name}/auth/getStatusList`,
            method: 'get'
        })
    } ,
    //取消预约
    cancelOrder(orderId){
        return request({
            url: `${api_name}/auth/cancelOrder/${orderId}`,
            method: 'get'
        })
    }

}