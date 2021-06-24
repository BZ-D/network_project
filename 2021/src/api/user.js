import axios from 'axios'
import {USER_MODULE} from "@/api/_prefix";

//POST 请求不会被缓存
//POST 请求不会保留在浏览器历史记录中
//POST 不能被收藏为书签
//POST 请求对数据长度没有要求
//GET 请求可被缓存
//GET 请求保留在浏览器历史记录中
//GET 请求可被收藏为书签
//GET 请求不应在处理敏感数据时使用
//GET 请求有长度限制
//GET 请求只应当用于取回数据（不修改）
export const login = payload => {
  console.log(payload)
  const {acNumber, password} = payload
  return axios.post(`${USER_MODULE}/login`, {acNumber, password}).then(res => {
    console.log(res)
    if (res.data.msg === '账号登陆成功！') {
      return res.data
    } else {
      return -1
    }
  })
}

export const signup = payload => {
  const {acNumber, uname, password} = payload
  console.log({acNumber, uname, password})
  return axios.post(`${USER_MODULE}/signup`, {acNumber, uname, password}).then(res => {
    return res.data
  })
}
export const getUserByUid = payload => {

  console.log(payload)
  return axios.get(`${USER_MODULE}/${payload}`).then(res => {
    return res.data
  })
}
