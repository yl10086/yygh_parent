import request from '@/utils/request'

const api_name = '/api/msm'

export default {
    //发送验证码接口
    sendCode(phone) {
        return request ({
            url: `${api_name}/sendCode/${phone}`,
            method: 'get'
        })
    }
}