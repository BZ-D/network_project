import axios from 'axios'

export const release = payload => {
  console.log(payload)
  const { userID, titleOfQN, questions } = payload
  return axios.post('后端提供的接收问卷信息的接口', { userID, titleOfQN, questions }).then(res => {
    return res.data
  })
}

export const draft = payload => {
  console.log(payload)
  const { userID, titleOfQN, questions } = payload
  return axios.post('后端提供的接收问卷信息的接口', { userID, titleOfQN, questions }).then(res => {
    return res.data
  })
}
