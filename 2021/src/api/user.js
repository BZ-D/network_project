import axios from 'axios'

export const login = payload => {
  console.log(payload)
  const { accountNumber, password } = payload
  return axios.post('后端提供的Controller', { accountNumber, password }).then(res => {
    return res.data
  })
}

export const signup = payload => {
  console.log(payload)
  const { accountNumber, nickname, password } = payload
  return axios.post('后端提供的Controller', { accountNumber, nickname, password }).then(res => {
    return res.data
  })
}
