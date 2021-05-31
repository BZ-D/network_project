import axios from "axios";

export const searchByTitle = payload => {
  console.log(payload)
  const { searchContent } = payload
  return axios.post('后端返回问卷信息的接口', { searchContent }).then(res => {
    return res.data
  })
}

export const searchByUser = payload => {
  console.log(payload)
  const { searchContent } = payload
  return axios.post('后端返回问卷信息的接口', { searchContent }).then(res => {
    return res.data
  })
}
