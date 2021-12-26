package com.example.board.service;

import com.example.board.domain.Like;
import com.example.board.dto.LikeDto;
import com.example.board.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final ModelMapper modelMapper;

    public LikeDto.Response createLike(LikeDto.Request requestLike){
        Like like = likeRepository.save(modelMapper.map(requestLike, Like.class));
        LikeDto.Response response = modelMapper.map(like, LikeDto.Response.class);
        return response;
    }

    public LikeDto.Response findById(Long id){
        Optional<Like> optLike = likeRepository.findById(id);
        LikeDto.Response responseLike = new LikeDto.Response();

        if (optLike.isPresent()){
            responseLike = modelMapper.map(optLike.get(), LikeDto.Response.class);
        }

        return responseLike;
    }

    public List<LikeDto.Response> findAll(){
        List<Like> likeList = likeRepository.findAll();
        List<LikeDto.Response> resultList = likeList.stream().map( l ->
            modelMapper.map(l, LikeDto.Response.class)
        ).collect(Collectors.toList());

        return resultList;
    }

    public List<LikeDto.Response> findByTargetId(Long targetId){
        List<Like> likeList = likeRepository.findLikesByTargetId(targetId);
        List<LikeDto.Response> resultList = likeList.stream().map( l ->
                modelMapper.map(l, LikeDto.Response.class)
        ).collect(Collectors.toList());

        return resultList;
    }

    public List<LikeDto.Response> findByMemberId(Long memberId){
        List<Like> likeList = likeRepository.findLikesByMemberId(memberId);
        List<LikeDto.Response> resultList = likeList.stream().map( l ->
                modelMapper.map(l, LikeDto.Response.class)
        ).collect(Collectors.toList());

        return resultList;
    }

    public LikeDto.Response updateLike(LikeDto.Info infoLike){

        Optional<Like> optLike = likeRepository.findById(infoLike.getLikeId());
        LikeDto.Response responseLike = new LikeDto.Response();

        if (optLike.isPresent()) {

            likeRepository.save(modelMapper.map(infoLike, Like.class));
            responseLike = modelMapper.map(infoLike, LikeDto.Response.class);
        }
        return responseLike;
    }

    public LikeDto.Response deleteLike(Long id){

        Optional<Like> optLike = likeRepository.findById(id);
        LikeDto.Response responseLike = new LikeDto.Response();

        if (optLike.isPresent()){
            likeRepository.deleteById(id);
            responseLike.setLikeId(id);
        }

        return responseLike;
    }

}
