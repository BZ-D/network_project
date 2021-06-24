import axios from "axios";
import {QUESTIONARE_MODULE} from './_prefix';

export const searchByTitle = payload => {
  console.log(payload)
  return axios.get(`${QUESTIONARE_MODULE}/searchByTitle?title=${payload}`).then(res => {
    return res.data
  })
}

export const searchByUser = payload => {
  console.log(payload)
  return axios.get(`${QUESTIONARE_MODULE}/searchByUser/${payload}`).then(res => {
    return res.data
  })
}

export const checkFilled = payload => {
  // 根据uid查询已填写问卷
  console.log(payload)
  return axios.get(`${QUESTIONARE_MODULE}/checkFilled/${payload}`).then(res => {
    return res.data
  })
}

export const checkReleased = payload => {
  console.log(payload)
  return axios.get(`${QUESTIONARE_MODULE}/checkReleased/${payload}`).then(res => {
    return res.data
  })
}

export const checkDraft = payload => {
  console.log(payload)
  return axios.get(`${QUESTIONARE_MODULE}/checkDraft/${payload}`).then(res => {
    return res.data
  })
}

export const release = payload => {
  console.log(payload)
  const {
    createUserID,
    titleOfQn,
    isDraft,
    numOfQuestions
  } = payload
  return axios.post(`${QUESTIONARE_MODULE}/release`, {
    createUserID,
    titleOfQn,
    isDraft,
    numOfQuestions
  }).then(res => {
    return res.data
  })
}

export const draft = payload => {
  console.log(payload)
  const {
    createUserID,
    titleOfQn,
    isDraft,
    numOfQuestions
  } = payload
  return axios.post('${QUESTIONARE_MODULE}/draft', {
    createUserID,
    titleOfQn,
    isDraft,
    numOfQuestions
  }).then(res => {
    return res.data
  })
}
