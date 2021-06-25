import axios from "axios";
import {ANSWER_MODULE} from "@/api/_prefix";

export const insertAnswers = payload => {
    console.log(payload)
    const {
        answerUserId,
        answerQnId,
        questionId,
        gapAnswer,
        singleAnswer,
        multiOne,
        multiTwo,
        multiThree,
        multiFour,
        multiFive,
        multiSix
    } = payload
    return axios.post(`${ANSWER_MODULE}/insertAnswers`,  {
        answerUserId,
        answerQnId,
        questionId,
        gapAnswer,
        singleAnswer,
        multiOne,
        multiTwo,
        multiThree,
        multiFour,
        multiFive,
        multiSix
    }).then(res => {
        return res.data
    })
}
export const getAnswers = payload => {
    // 根据qnid和uid找到该用户的答案
    console.log(payload)
    return axios.get(`${ANSWER_MODULE}/getAnswers/${payload.qnId}?uid=${payload.uid}`).then(res => {
        return res.data
    })
}